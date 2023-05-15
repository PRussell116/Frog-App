package com.example.amphibians.ui.screens


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibians.FrogApplication
import com.example.amphibians.model.Amphibian
import com.example.amphibians.data.FrogRepo
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface FrogUiState{
    data class Success(val data:List<Amphibian>):FrogUiState

    object Error: FrogUiState

    object  Loading: FrogUiState
}

class FrogViewModel(private val frogRepo : FrogRepo): ViewModel(){
    var frogUiState: FrogUiState by mutableStateOf(FrogUiState.Loading)
        private set

    init {
        getFrogData()
    }
    fun getFrogData(){
        viewModelScope.launch {
            frogUiState = FrogUiState.Loading
            frogUiState = try{

                FrogUiState.Success(frogRepo.getFrogData())
            }catch (e : IOException){
                FrogUiState.Error
            }catch (e: HttpException){
                FrogUiState.Error
            }
        }
    }
    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FrogApplication)
                val frogRepo = application.container.frogRepo
                FrogViewModel(frogRepo = frogRepo)
            }
        }

    }
}