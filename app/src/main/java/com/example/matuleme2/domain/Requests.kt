package com.example.matuleme2.domain

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.matuleme2.data.models.Favourite
import com.example.matuleme2.data.models.Sneaker
import com.example.matuleme2.domain.repository.UserRepository
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch
import java.util.UUID

object Requests {

    suspend fun getAllSneakers(): List<Sneaker> {
        return Constants.supabase.from("sneakers").select().decodeList<Sneaker>()
    }

    suspend fun getIdFavSneakers(): List<String> {
        return Constants.supabase.from("favorites")
            .select {
                filter {
                    eq("id_user", UserRepository.uuidCurrentUser)
                }
            }.decodeList<Favourite>().toList().map { it.id_sneaker }
    }

    suspend fun addSneakerInFav(sneakerId: String) {
        val favItem = Favourite(UUID.randomUUID().toString(), sneakerId, UserRepository.uuidCurrentUser)
        Constants.supabase.from("favorites").insert(favItem)
    }

    suspend fun deleteFavItem(sneakerId: String) {
        Constants.supabase.from("favorites").delete {
            filter {
                eq("id_user", UserRepository.uuidCurrentUser)
                eq("id_sneaker", sneakerId)
            }
        }
    }
}