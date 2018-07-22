package com.example.godtfoodapp.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Recipe(@SerializedName("title") var title: String = "",
                  @SerializedName("description") var description: String = "",
                  @Ignore @SerializedName("basicPortionNumber") var basicPortionNumber: Int = 0,
                  @Ignore @SerializedName("preparationTime") var preparationTime: Int = 0,
                  @Ignore @SerializedName("numberOfComments") var numberOfComments: Int = 0,
                  @Ignore @SerializedName("numberOfLikes") var numberOfLikes: Int = 0,
                  @Ignore @SerializedName("publishedAt") var publishedAt: String = "",
                  @Ignore @SerializedName("status") var status: String = "",
                  @Ignore @SerializedName("updatedAt") var updatedAt: String = "",
                  @Ignore @SerializedName("embedTitle") var embedTitle: Any = Any(),
                  @Ignore @SerializedName("canonicalUrl") var canonicalUrl: Any = Any(),
                  @field: PrimaryKey @SerializedName("id") var id: Int = 0,
                  @Ignore @SerializedName("menyExported") var menyExported: Any = Any(),
                  @Ignore @SerializedName("user") var user: User = User(),
                  @SerializedName("ingredients") var ingredients: List<Ingredient> = listOf(),
                  @Ignore @SerializedName("tags") var tags: List<Tag> = listOf(),
                  @Ignore @SerializedName("steps") var steps: List<Step> = listOf(),
                  @SerializedName("images") var images: List<Image> = listOf(),
                  @Ignore @SerializedName("links") var links: Any = Any()
) {

    data class User(
            @SerializedName("id") var id: Int = 0,
            @SerializedName("displayName") var displayName: String = "",
            @SerializedName("image") var image: String = "",
            @SerializedName("email") var email: Any = Any(),
            @SerializedName("receiveNotifications") var receiveNotifications: Int = 0,
            @SerializedName("role") var role: String = ""
    )


    data class Image(
            @Ignore @SerializedName("imboId") var imboId: String = "",
            @SerializedName("url") var url: String = ""
    )


    data class Ingredient(
            @Ignore @SerializedName("id") var id: Any = Any(),
            @SerializedName("name") var name: String = "",
            @SerializedName("elements") var elements: List<Element> = listOf()
    ) {

        data class Element(
                @Ignore @SerializedName("id") var id: Int = 0,
                @Ignore @SerializedName("amount") var amount: Double = 0.0,
                @Ignore @SerializedName("hint") var hint: String = "",
                @SerializedName("name") var name: String = "",
                @Ignore @SerializedName("unitName") var unitName: String = "",
                @Ignore @SerializedName("symbol") var symbol: String = "",
                @Ignore @SerializedName("menyCategory") var menyCategory: MenyCategory = MenyCategory()
        ) {

            data class MenyCategory(
                    @SerializedName("id") var id: String = "",
                    @SerializedName("name") var name: String = ""
            )
        }
    }


    data class Tag(
            @SerializedName("id") var id: String = "",
            @SerializedName("name") var name: String = ""
    )


    data class Step(
            @SerializedName("id") var id: Int = 0,
            @SerializedName("heading") var heading: Any = Any(),
            @SerializedName("description") var description: String = "",
            @SerializedName("image") var image: Any = Any(),
            @SerializedName("video") var video: Any = Any()
    )
}