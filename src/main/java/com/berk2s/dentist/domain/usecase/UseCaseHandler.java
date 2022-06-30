package com.berk2s.dentist.domain.usecase;

public interface UseCaseHandler<E, T extends UseCase> {

    E handle(T t);
}
