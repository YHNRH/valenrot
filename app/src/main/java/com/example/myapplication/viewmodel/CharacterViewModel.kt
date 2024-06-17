package com.example.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.myapplication.core.room.AppDatabase
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.viewModelScope
import com.example.myapplication.core.room.entity.Character
import com.example.myapplication.core.room.repo.CharacterRepository
import kotlinx.coroutines.launch

class CharacterViewModel(application: Application) : AndroidViewModel(application) {
    val allCharacters : LiveData<List<Character>>
    private val repository : CharacterRepository

    init {
        val dao = AppDatabase.getDatabase(application).getCharacterDao()
        repository = CharacterRepository(dao)
        allCharacters = repository.allCharacters
    }
    fun deleteCharacter(character: Character) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(character)
    }
    fun updateCharacter(character: Character) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(character)
    }
    fun addCharacter(character: Character) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(character)
    }
}