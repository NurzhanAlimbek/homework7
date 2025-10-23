package Patterns;

abstract class Beverage {
    public final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        if (customerWantsCondiments()) {
            addCondiments();
        }
    }

    protected abstract void brew();
    protected abstract void addCondiments();

    protected void boilWater() {
        System.out.println("Кипячение воды");
    }

    protected void pourInCup() {
        System.out.println("Наливание в чашку");
    }

    protected boolean customerWantsCondiments() {
        return true;
    }
}

class Tea extends Beverage {
    protected void brew() {
        System.out.println("Заваривание чая");
    }

    protected void addCondiments() {
        System.out.println("Добавление лимона");
    }
}

class Coffee extends Beverage {
    protected void brew() {
        System.out.println("Заваривание кофе");
    }

    protected void addCondiments() {
        System.out.println("Добавление сахара и молока");
    }

    protected boolean customerWantsCondiments() {
        return false;
    }
}


class TemplateDemo {
    public static void main(String[] args) {
        Beverage tea = new Tea();
        Beverage coffee = new Coffee();

        System.out.println("Приготовление чая:");
        tea.prepareRecipe();

        System.out.println("\nПриготовление кофе:");
        coffee.prepareRecipe();
    }
}

