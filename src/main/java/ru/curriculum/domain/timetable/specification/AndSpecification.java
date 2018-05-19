package ru.curriculum.domain.timetable.specification;

import java.util.List;

public class AndSpecification<Entity> extends CompositeSpecification<Entity> {
    private ISpecification<Entity> left;
    private ISpecification right;

    public AndSpecification(ISpecification<Entity> left, ISpecification<Entity> right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public ResultOfApplySpecification isSatisfiedBy(Entity o) {
        ResultOfApplySpecification resultOfLeft = left.isSatisfiedBy(o);
        ResultOfApplySpecification resultOfRight = right.isSatisfiedBy(o);
        List<String> errors = resultOfLeft.getErrors();
        List<String> warnings = resultOfLeft.getWarnings();
        errors.addAll(resultOfRight.getErrors());
        warnings.addAll(resultOfRight.getWarnings());
        return new ResultOfApplySpecification(errors, warnings);
    }
}
