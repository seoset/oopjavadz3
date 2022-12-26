package service;

import machine.VendingMachine;
import product.Product;
import repository.VendingMachineRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class VendingMachineService {
    private final VendingMachineRepository repository = new VendingMachineRepository();

    public List<Product> getProducts(String key) {
        return repository.get(key).getProducts();
    }

    public VendingMachine getMachine(String key) {
        return repository.get(key);
    }

    public String reserveProducts(VendingMachine machine, List<Product> products) {
        String code = generateString(new Random());
        machine.reserveProducts(code, products);
        return code;
    }

    private static String generateString(Random rng)
    {
        char[] text = new char[6];
        for (int i = 0; i < 6; i++)
        {
            text[i] = "0123456789ABCDEF".charAt(rng.nextInt("0123456789ABCDEF".length()));
        }
        return new String(text);
    }

    public List<Product> getProductsByCode(String key, String code) {
        return repository.get(key).reserveProducts(code);
    }

    public Product getBestProduct(String machine) {
        VendingMachine vm = repository.get(machine);
        return vm.getProducts().stream().max(Comparator.comparingInt(Product::getPrice)).get();
    }
}
