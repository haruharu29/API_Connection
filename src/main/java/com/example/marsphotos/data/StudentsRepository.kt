package com.example.marsphotos.data

import com.example.marsphotos.model.Student
import com.example.marsphotos.network.MarsApiService

/**
 * Repository that fetch mars photos list from marsApi.
 */
interface StudentsRepository {
    /** Fetches list of MarsPhoto from marsApi */
    //suspend fun getStudents(): List<Student>
    suspend fun getStudents(): List<Student>
}

/**
 * Network Implementation of Repository that fetch mars photos list from marsApi.
 */
class NetworkStudentsRepository(
    private val marsApiService: MarsApiService
) : StudentsRepository {
//    override suspend fun getStudents(): List<Student> = marsApiService.getStudents(true)
override suspend fun getStudents(): List<Student> = marsApiService.getStudents(true)
}