package com.app.motivationapp.domain.repository

import com.app.motivationapp.data.remote.Motivation
import com.app.motivationapp.data.remote.MotivationResult
import kotlinx.coroutines.flow.Flow


interface MotivationRepository{


    fun getFacts() : Flow<MotivationResult<Motivation>>



}