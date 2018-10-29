package com.donguyen.domain.repository

import com.donguyen.domain.model.User
import com.donguyen.domain.usecase.Result
import com.donguyen.domain.util.None
import io.reactivex.Observable

/**
 * Created by DoNguyen on 23/10/18.
 */
interface UserRepository {

    fun insertUsers(users: List<User>): Observable<Result<None>>

    fun getUsers(): Observable<Result<List<User>>>
}