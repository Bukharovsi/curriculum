package ru.curriculum.domain.timetable.specification;

public class AndSpecification<Entity> extends CompositeSpecification<Entity> {
    private ISpecification<Entity> left;
    private ISpecification right;

    public AndSpecification(ISpecification<Entity> left, ISpecification<Entity> right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public ResultOfApplySpecification isSatisfiedBy(Entity o) {
        ResultOfApplySpecification resultOfApplySpecification = left.isSatisfiedBy(o);
        resultOfApplySpecification.addErrors(right.isSatisfiedBy(o).getErrorMessages());
        return resultOfApplySpecification;
    }
}
