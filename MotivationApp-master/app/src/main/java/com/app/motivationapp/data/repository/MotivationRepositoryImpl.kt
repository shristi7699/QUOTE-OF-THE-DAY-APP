package com.app.motivationapp.data.repository

import android.util.Log
import com.app.motivationapp.data.remote.Motivation
import com.app.motivationapp.data.remote.MotivationApi
import com.app.motivationapp.data.remote.MotivationResult
import com.app.motivationapp.domain.repository.MotivationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class MotivationRepositoryImpl(
    private val motivationApi: MotivationApi
) : MotivationRepository {
    override fun getFacts(): Flow<MotivationResult<Motivation>> {
        return flow {
            val result = try {
                motivationApi.getFacts()
            } catch (e: Exception) {
                Log.d("pokemon", "getFacts: ${e.message}")
                emit(MotivationResult.Failure(e.message ?: "Something went wrong"))
                return@flow
            }
            emit(MotivationResult.Success(data = result))
        }
    }
}

