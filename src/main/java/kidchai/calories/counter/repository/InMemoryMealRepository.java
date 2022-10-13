package kidchai.calories.counter.repository;

import kidchai.calories.counter.model.Meal;
import kidchai.calories.counter.util.MealsUtil;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryMealRepository implements MealRepository {
    private final ConcurrentMap<Integer, Meal> mealMap = new ConcurrentHashMap<>();
    public static final AtomicInteger index = new AtomicInteger();

    {
        MealsUtil.getAllMeals().forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if (!meal.hasId()) {
            meal.setId(index.getAndIncrement());
            mealMap.put(meal.getId(), meal);
            return meal;
        }

        return mealMap.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public Meal get(int id) {
        return mealMap.get(id);
    }

    @Override
    public void remove(int id) {
        mealMap.remove(id);
    }

    @Override
    public Collection<Meal> getAll() {
        return mealMap.values();
    }
}
