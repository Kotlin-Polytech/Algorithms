package lesson7;

import kotlin.NotImplementedError;
import lesson5.Graph;
import lesson5.Path;
import lesson6.knapsack.Fill;
import lesson6.knapsack.Item;

import java.util.List;

// Примечание: в этом уроке достаточно решить одну задачу
@SuppressWarnings("unused")
public class JavaHeuristicsTasks {

    /**
     * Решить задачу о ранце (см. урок 6) любым эвристическим методом
     *
     * Очень сложная
     *
     * load - общая вместимость ранца, items - список предметов
     *
     * Используйте parameters для передачи дополнительных параметров алгоритма
     * (не забудьте изменить тесты так, чтобы они передавали эти параметры)
     */
    public static Fill fillKnapsackHeuristics(int load, List<Item> items, Object... parameters) {
        throw new NotImplementedError();
    }

    /**
     * Решить задачу коммивояжёра (см. урок 5) методом колонии муравьёв
     * или любым другим эвристическим методом, кроме генетического и имитации отжига
     * (этими двумя методами задача уже решена в под-пакетах annealing & genetic).
     *
     * Очень сложная
     *
     * Граф передаётся через параметр graph
     *
     * Используйте parameters для передачи дополнительных параметров алгоритма
     * (не забудьте изменить тесты так, чтобы они передавали эти параметры)
     */
    public static Path findVoyagingPathHeuristics(Graph graph, Object... parameters) {
        throw new NotImplementedError();
    }
}
