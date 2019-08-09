package com.example.finalproject.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * 05 Create your data class.
 *
 * Ensure your data class matches the data(JSON, XML, HTML)
 * you receive, if you are connecting to an endpoint.
 *
 * This particular project does use an endpoint for it's data.
 * Samples of the JSON are in the readme package, in the
 * SampleJson.json file.
 *
 * @author Marshall Ladd
 */
// 05 This creates our class, and defines our constructor, all in one line
data class Employee(

    val id: Int,
    val employee_name: String,
    val email: String,
    val job: String,
    val salary: String,
val age: Int)

