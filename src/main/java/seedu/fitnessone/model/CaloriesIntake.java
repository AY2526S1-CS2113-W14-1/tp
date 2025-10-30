package seedu.fitnessone.model;

import java.time.LocalDate;

public class CaloriesIntake {
    private final LocalDate date; // YYYY-MM-DD
    private final int carbs;
    private final int protein;
    private final int fat;

    public CaloriesIntake(LocalDate date, int carbs, int protein, int fat) {
        this.date = date;
        this.carbs = carbs;
        this.protein = protein;
        this.fat = fat;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getCarbs() {
        return carbs;
    }

    public int getProtein() {
        return protein;
    }

    public int getFat() {
        return fat;
    }

    @Override
    public String toString() {
        return String.format("Date: %s | Carbs: %d | Protein: %d | Fat: %d",
                date.toString(), carbs, protein, fat);
    }
}
