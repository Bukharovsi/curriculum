package ru.curriculum.domain.timetable.specification;

public abstract class CompositeSpecification<Entity> implements ISpecification<Entity> {

    @Override
    public ISpecification and(ISpecification<Entity> other) {
        return new AndSpecification(this, other);
    }
}
