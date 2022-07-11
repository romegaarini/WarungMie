package org.d3if0031.warungmie.data

object InMemoryUserDataStore {

    val users = mutableMapOf<String, User>()

    fun login(username: String, password: String): User? {
        val loginUser = users[username] ?: return null

        if (password != loginUser.password) {
            return null
        }

        return loginUser
    }

    fun register(user: User): Boolean {
        val registerUser = users[user.username]

        if (registerUser != null) {
            return false
        }

        users[user.username] = user
        return true
    }
}