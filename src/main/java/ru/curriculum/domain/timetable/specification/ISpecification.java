package ru.curriculum.domain.timetable.specification;

public interface ISpecification<Entity> {

    ResultOfApplySpecification isSatisfiedBy(Entity entity);

    ISpecification<Entity> and(ISpecification<Entity> other);
}
