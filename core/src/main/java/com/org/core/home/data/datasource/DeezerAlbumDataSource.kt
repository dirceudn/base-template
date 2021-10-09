package com.org.core.home.data.datasource

import com.org.core.common.DefaultErrorDataModel
import com.org.core.common.Either
import com.org.core.home.data.model.DeezerAlbumDataModel

interface DeezerAlbumDataSource {

    suspend fun getAlbums(): Either<DefaultErrorDataModel, DeezerAlbumDataModel>
}