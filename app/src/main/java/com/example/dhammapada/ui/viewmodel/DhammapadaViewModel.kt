package com.example.dhammapada.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dhammapada.ui.data.DhammapadaRecord
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject

class DhammapadaViewModel : ViewModel() {

    private var records = MutableStateFlow<List<DhammapadaRecord>>(emptyList())
    var _records: StateFlow<List<DhammapadaRecord>> = records.asStateFlow()

    private val _currentRecordId = MutableStateFlow(1)
    val currentRecordId: StateFlow<Int> = _currentRecordId

    val maxRecordId: Int = 422

    fun changeRecordId(newId: Int) {
        _currentRecordId.value = newId
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
            records.value = recordsList
        }
    }

    fun getTextById(id: Int): String? {
        return records.value.find { it.id == id }?.text
    }

    fun getImageById(id: Int): String? {
        return records.value.find { it.id == id }?.photo
    }
}