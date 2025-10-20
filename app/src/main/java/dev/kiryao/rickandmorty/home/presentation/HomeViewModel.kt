package dev.kiryao.rickandmorty.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.kiryao.rickandmorty.core.data.local.RMEntity
import dev.kiryao.rickandmorty.core.data.mapper.toRMItem
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    pager: Pager<Int, RMEntity>
): ViewModel() {

    val characterPagerFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toRMItem() }
        }
        .cachedIn(viewModelScope)
}