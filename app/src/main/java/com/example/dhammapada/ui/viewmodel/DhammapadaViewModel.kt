package com.example.dhammapada.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dhammapada.ui.data.DhammapadaRecord
import com.example.dhammapada.ui.theme.START
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import kotlin.random.Random

class DhammapadaViewModel : ViewModel() {

    private var _records = MutableStateFlow<List<DhammapadaRecord>>(emptyList())
    //var records: StateFlow<List<DhammapadaRecord>> = _records.asStateFlow()

    private val _currentRecordId = MutableStateFlow(1)
    val currentRecordId: StateFlow<Int> = _currentRecordId

    private val _currentText = MutableStateFlow<String?>(START)
    val currentText: StateFlow<String?> = _currentText.asStateFlow()

    private val _currentImageName = MutableStateFlow<String?>("photos/photo_1@09-10-2019_10-26-14.jpg")
    val currentImageName: StateFlow<String?> = _currentImageName.asStateFlow()

    val maxRecordId: Int = 422

    fun changeRecordId(newId: Int) {
        _currentRecordId.value = newId
        _currentText.value = _records.value.find { extractNumber(it.text) == newId }?.text
        _currentImageName.value = _records.value.find { extractNumber(it.text) == newId }?.photo
    }

    fun adviceFun(){
        val newId = Random.Default.nextInt(1, 423)
        _currentRecordId.value = newId
        _currentText.value = _records.value.find { extractNumber(it.text) == newId }?.text
        _currentImageName.value = _records.value.find { extractNumber(it.text) == newId }?.photo
    }

    private fun extractNumber(record: String): Int {
        val digitsOnly = record.filter { it.isDigit() }
        return if (digitsOnly.isNotEmpty()) {
            digitsOnly.toInt()
        } else {
            0
        }
    }

    fun loadData(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val jsonString = context.assets.open("dhammapada.json").bufferedReader().use { it.readText() }
            val jsonObject = JSONObject(jsonString)
            val jsonArray = jsonObject.getJSONArray("messages")
            val recordsList = mutableListOf<DhammapadaRecord>()
            for (i in 0 until jsonArray.length()) {
                val messageObject = jsonArray.getJSONObject(i)
                if (messageObject.getString("type") == "message") {
                    recordsList.add(DhammapadaRecord.fromJson(messageObject))
                }
            }
            _records.value = recordsList
        }
    }

}