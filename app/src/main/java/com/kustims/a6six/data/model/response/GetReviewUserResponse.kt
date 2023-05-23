package com.kustims.a6six.data.model.response

import android.provider.ContactsContract.RawContacts.Data

import com.kustims.a6six.domain.model.Review
import kotlinx.serialization.json.JsonNull.content

data class GetReviewResponse(
    val data: Data
)


