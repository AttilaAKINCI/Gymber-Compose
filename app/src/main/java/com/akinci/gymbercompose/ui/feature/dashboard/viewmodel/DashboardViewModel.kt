package com.akinci.gymbercompose.ui.feature.dashboard.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akinci.gymbercompose.common.coroutine.CoroutineContextProvider
import com.akinci.gymbercompose.common.helper.PartnerMatchProvider
import com.akinci.gymbercompose.common.helper.state.UIState
import com.akinci.gymbercompose.common.network.NetworkChecker
import com.akinci.gymbercompose.common.network.NetworkResponse
import com.akinci.gymbercompose.data.output.Partner
import com.akinci.gymbercompose.data.repository.PartnerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val coroutineContext: CoroutineContextProvider,
    private val partnerRepository: PartnerRepository
): ViewModel() {

    /** controls match state of UI **/
    var matchState by mutableStateOf(false)
        private set

    /** controls loading and error states in UI **/
    var informer by mutableStateOf<UIState>(UIState.None)
        private set

    /** controls data state in UI **/
    var partnerState by mutableStateOf<Partner?>(null)
        private set

    var partnerListState by mutableStateOf(listOf<Partner>())
        private set

    init {
        Timber.d("SharedViewModel(DashboardViewModel) created..")
        getPartnerList()
    }

    fun dismissMatchState(){ matchState = false }
    fun setMatchState(){
        matchState = true
        partnerState = partnerListState[0]
    }


//    fun getTopItem(): Partner{ return partnerList[0] }
//    fun getLastSwipedItem(): Partner? { return if(swipedItems.isNotEmpty()) { swipedItems.last() } else { null } }
//    fun removeItem(){
//        if(partnerList.isNotEmpty()) {
//            swipedItems.add(partnerList[0])
//            partnerList.removeAt(0)
//        }
//
//        viewModelScope.launch(coroutineContext.IO) {
//          //  _partnerListData.emit(ListState.OnData(partnerList))
//        }
//    }

    fun getPartnerList(){
        Timber.d("DashboardViewModel:: getPartnerList called")
        if(partnerListState.isEmpty()){
            viewModelScope.launch(coroutineContext.IO) {
                partnerRepository.getPartnerList().collect { networkResponse ->
                    when(networkResponse){
                        is NetworkResponse.Loading -> {
                            Timber.d("DashboardViewModel:: Partner list loading..")
                            withContext(this@DashboardViewModel.coroutineContext.Main) { informer = UIState.OnLoading }
                        }
                        is NetworkResponse.Error -> {
                            Timber.d("DashboardViewModel:: Partner list service error..")
                            withContext(this@DashboardViewModel.coroutineContext.Main) { informer = UIState.OnServiceError }
                        }
                        is NetworkResponse.Success -> {
                            networkResponse.data?.let {
                                Timber.d("Partner list fetched size:-> ${it.data}")
                                partnerListState = PartnerMatchProvider.createAMatchPattern(it.data)
                            }
                        }
                    }
                }
            }
        }
    }
}