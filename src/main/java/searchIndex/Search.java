package searchIndex;

import products.Product;

import java.io.Serializable;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Search implements Serializable {
    private Map<String, List<Product>> index;

    public Search() {
        index = new HashMap<>();
    }

    public Map<String, List<Product>> getIndex() {
        return index;
    }

    public void add(Product product) {
        String[] words = product.getName().split("\\s");
        Set<String> keys = index.keySet();
        for (String s : words) {
            if (keys.contains(s)) {
                List<Product> products = index.get(s);
                products.add(product);
            } else {
                index.put(s, new ArrayList<>(Arrays.asList(product)));
            }
        }
    }

    public void delete(String name) {
        name = name.toLowerCase();
        if (index.containsKey(name)) {
            for (Product product : index.get(name)) {
                index.get(name).remove(product);
            }
            if (index.get(name).isEmpty()) {
                index.remove(name);
            }
        }
    }

    public void delete(Predicate<String> predicate) {
        Set<Product> result = new HashSet<>();
        for (String key : index.keySet()) {
            if (predicate.test(key)) {
                index.get(key).forEach(product -> {
                    if (predicate.test(product.getName())) {
                        result.add(product);
                    }
                });
            }
        }
        for (Map.Entry<String, List<Product>> entry : index.entrySet()) {
            for (Product p : result) {
                entry.getValue().remove(p);
            }
        }
    }

    public List<Product> find(Predicate<String> predicate) {
        List<Product> result = new ArrayList<>();
        for (String key : index.keySet()) {
            if (predicate.test(key)) {
                index.get(key).forEach(p -> result.add(p));
            }
        }
        return result;
    }

    public List<Product> findByPattern(String pattern) {
        if (pattern.equals("*")) {
            return find(s -> !s.isEmpty());
        } else if (!pattern.contains("*")) {
            return find(s -> s.equals(pattern));
        } else if (pattern.startsWith("*")) {
            return find(s -> s.endsWith(pattern.substring(1)));
        } else if (pattern.endsWith("*")) {
            return find(s -> s.startsWith(pattern.substring(0, pattern.length() - 1)));
        } else {
            String firstHalf = pattern.substring(0, pattern.indexOf('*'));
            String secondHalf = pattern.substring(pattern.indexOf('*') + 1);
            return find(s -> s.startsWith(firstHalf) && s.endsWith(secondHalf));
        }
    }

    public List<Product> findByWorld(String key) {
        return index.get(key);
    }

    public Stream<Product> streamOfComp(Product product) {
        return Stream.of(product)
                .flatMap(p -> Arrays.asList(p.getName().toLowerCase().split("\\s")).stream())
                .flatMap(s -> index.get(s).stream())
                .collect(Collectors.toMap(t -> t, count -> 1, (a, b) -> a + 1))
                .entrySet()
                .stream()
                .sorted((p1, p2) -> -(p1.getValue()).compareTo(p2.getValue()))
                .map(p -> p.getKey())
                .filter(p -> !p.equals(product));
    }
}
