package ru.job4j.ood.ocp.ex;

public class ExampleOCP {
    private static class Bachelor {
        private int age;

        public int returnAge() {
            return this.age;
        }
    }

    // Класс изначально не унаследован. А вдруг понадобится добавить добавить ещё классы
    // магистров, специалистов или аспирантов

    private static class Cat {
        public String sound() {
            return "Meow";
        }
    }

    private static class Dog {
        public String sound() {
            return "Bark";
        }
    }
    //Классы Cat и Dog можно наследовать от интерфейса Animal, например,
    //а не создавать отдельные классы для одной и той же задачи.

    private interface AutoTransport {
        double returnEngineCapacity();
    }

    private static class Car implements AutoTransport {
        private double engineCapacity;

        @Override
        public double returnEngineCapacity() {
            return engineCapacity;
        }
    }

    private static class Truck {
        private int wheelsNumber;

        private double engineCapacity;

        public double returnEngineCapacity() {
            return engineCapacity;
        }
    }

    //Truck может быть унаследован от интерфейса транспорта
}
