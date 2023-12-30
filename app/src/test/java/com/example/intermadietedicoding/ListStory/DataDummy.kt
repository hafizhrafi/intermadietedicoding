package com.example.intermadietedicoding.ListStory

import com.example.intermadietedicoding.response.GeneralResponseHandler
import com.example.intermadietedicoding.response.GettAllStoriesHandler

object DataDummy {


    fun generateDummyStoryResponse(): GeneralResponseHandler {
        val asset = listOf<GettAllStoriesHandler>(
            GettAllStoriesHandler(
                "story-JTEiQgK-rKY2TrGK",
                "no",
                "hi",
                "https://story-api.dicoding.dev/images/stories/photos-1703946675797_H7LdKQyT.jpg",
                "2023-12-30T14:31:15.799Z",
                (-7.4303543).toString(),
                (109.2477913).toString()
            ),
            GettAllStoriesHandler(
                "story-s7GHTFTnYSsfidMk",
                "aisyacans",
                "cute",
                "https://story-api.dicoding.dev/images/stories/photos-1703946656493_T_bk8VVn.jpg",
                "2023-12-30T14:30:56.496Z",
                null,
                null
            ),
            GettAllStoriesHandler(
                "story-i-j1K4rXnVPocMvt",
                "aisyacans",
                "bumbu",
                "https://story-api.dicoding.dev/images/stories/photos-1703946553921_EHpzn4FX.jpg",
                "2023-12-30T14:29:13.922Z",
                null,
                null
            ),
            GettAllStoriesHandler(
                "story-cXTePLfdj19o0YZq",
                "aisyacans",
                "donut",
                "https://story-api.dicoding.dev/images/stories/photos-1703946483768_UNBR-Tgg.jpg",
                "2023-12-30T14:28:03.772Z",
                null,
                null
            ),
            GettAllStoriesHandler(
                "story-IAQmeNg7X4mKAZQ7",
                "aisyacans",
                "dijual murah ",
                "https://story-api.dicoding.dev/images/stories/photos-1703946416545_N0iY4g7n.jpg",
                "2023-12-30T14:26:56.547Z",
                (-7.7534573).toString(),
                (110.4013607).toString()
            ),
            GettAllStoriesHandler(
                "story-q_1yjn9eUASgGxJP",
                "abc",
                "y",
                "https://story-api.dicoding.dev/images/stories/photos-1703945844363_YwUVMeWP.jpg",
                "2023-12-30T14:17:24.365Z",
                (-7.5603202).toString(),
                (110.7417245).toString()
            ),
            GettAllStoriesHandler(
                "story-C0ymXQFF0UxxXNWe",
                "hehehehe ",
                "malam",
                "https://story-api.dicoding.dev/images/stories/photos-1703944875743_1bhuF0Oz.jpg",
                "2023-12-30T14:01:15.748Z",
                (-7.7600477).toString(),
                (110.3713925).toString()
            ),
            GettAllStoriesHandler(
                "story-WUwDmmMkBo9NtcNt",
                "reviewer",
                "test",
                "https://story-api.dicoding.dev/images/stories/photos-1703944209579_ANGOO2uH.jpg",
                "2023-12-30T13:50:09.582Z",
                null,
                null
            ),
            GettAllStoriesHandler(
                "story-iA0t9Vxy22xlZNYg",
                "kamus",
                "Cek",
                "https://story-api.dicoding.dev/images/stories/photos-1703943597955_B-CJLpal.jpg",
                "2023-12-30T13:39:57.958Z",
                null,
                null
            ),
            GettAllStoriesHandler(
                "story-9Bu3rdJ8l3IVfLVZ",
                "chintia",
                "test",
                "https://story-api.dicoding.dev/images/stories/photos-1703943297666_wvSEG5z2.jpg",
                "2023-12-30T13:34:57.668Z",
                null,
                null
            )
        )

        return GeneralResponseHandler(
            error = false,
            massage  = "Stories fetched successfully",
            listStory = asset
        )
    }
}