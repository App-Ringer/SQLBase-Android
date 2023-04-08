Tag: [![](https://jitpack.io/v/App-Ringer/SQLBase-Android.svg)](https://jitpack.io/#App-Ringer/SQLBase-Android)

# SqlBase

SqlBase is a library that provides a convenient API for performing common operations on an SQL database. It includes functionality for inserting and retrieving data, as well as user authentication.

## Installation

Add it in your root build.gradle at the end of repositories:

```gradle
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

To use SqlBase in your project, you can add the following dependency to your `build.gradle` file:

```gradle
dependencies {
    implementation 'com.github.App-Ringer:SQLBase-Android:Tag'
}
```

## Usage

Before using SqlBase, you need to set the BASE_URL property in the SqlBaseConfig class to the appropriate value for the server hosting the remote SQL database. This can be done in your application class, which is typically initialized when the application starts up.

```kotlin
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        SqlBaseConfig.BASE_URL = "https://my-server.com"
    }
}
```

## Authentication

You can use the login and logout methods to perform user authentication:

### Login

```kotlin
val token = "my_token"
val countryCode = 1
val mobileNumber = "555-1234"
val uid = "1234"
SqlBase.Database()
    .login(token, countryCode, mobileNumber, uid, context)
    .addOnSuccessListener { isSuccessful, body ->
        // Handle successful login
    }
    .addOnFailureListener { exception ->
        // Handle login failure
    }
```

This code logs in a user with the specified token, countryCode, mobileNumber, and uid.

### Logout

```kotlin
SqlBase.Database()
    .logout(context)
```

This code logs out the currently logged in user.

## Usage

Before using SqlBase, you need to set the BASE_URL property in the SqlBaseConfig class to the appropriate value for the server hosting the remote SQL database. This can be done in your application class, which is typically initialized when the application starts up.

```kotlin
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        SqlBaseConfig.BASE_URL = "https://my-server.com"
    }
}
```

Once you have set the BASE_URL property, you can start using SqlBase by creating a new instance of the SqlBase.Database class and using its methods to build SQL queries.

```kotlin
val data = MyDataClass()
SqlBase.Database()
    .table("my_table")
    .whereEqualTo("field1", "value1")
    .whereEqualTo("field2", "value2")
    .addOnSuccessListener { isSuccessful, body ->
        // Handle successful response
    }
    .addOnFailureListener { exception ->
        // Handle failure
    }
    .set(data, context)
```

This code builds an SQL query to insert data into the my_table table, where field1 is equal to "value1" and field2 is equal to "value2". The addOnSuccessListener and addOnFailureListener methods set callbacks to be executed when the operation completes successfully or fails, respectively.

You can also use the get method to perform a select operation and retrieve data from the database:

```kotlin
SqlBase.Database()
    .table("my_table")
    .whereEqualTo("field1", "value1")
    .orderBy("field2", Query.Direction.DESCENDING)
    .limit(10)
    .offset(0)
    .addOnSuccessListener { isSuccessful, body ->
        // Handle successful response
    }
    .addOnFailureListener { exception ->
        // Handle failure
    }
    .get(context)
```

This code builds an SQL query to retrieve data from the my_table table, where field1 is equal to "value1", orders the results by field2 in descending order, limits the results to 10, and offsets the results by 0.

The deleteAccount method of the Database class is used to delete a user account from the SQL database.

Parameters
userId - The ID of the user account to be deleted.
context - The Context object used to access the Android application environment.

```kotlin
    SqlBase.Database()
    .addOnSuccessListener { isSuccessful, body ->
        // Handle successful response
    }
    .addOnFailureListener {
        // Handle failure
    }
    .deleteAccount(
        userId,
        context = MyApplication.context()
    )
```

This code deletes the user account with the specified ID from the SQL database and sets callbacks to be executed when the operation completes successfully or fails, respectively.