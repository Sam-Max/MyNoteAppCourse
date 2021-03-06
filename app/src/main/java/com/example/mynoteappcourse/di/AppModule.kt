package com.example.mynoteappcourse.di

import android.app.Application
import androidx.room.Room
import com.example.mynoteappcourse.feature_note.data.data_source.room.NoteDatabase
import com.example.mynoteappcourse.feature_note.data.repository.NoteRepositoryImpl
import com.example.mynoteappcourse.feature_note.domain.repository.NoteRepository
import com.example.mynoteappcourse.feature_note.domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideNoteDb(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repo: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotesUseCase = GetNotesUseCase(repo),
            deleteNoteUseCase = DeleteNoteUseCase(repo),
            getNoteUseCase = GetNoteUseCase(repo),
            insertNoteUseCase = InsertNoteUseCase(repo)
        )
    }

}