package virgilistrate.CapstoneProject.specifications;

import org.springframework.data.jpa.domain.Specification;
import virgilistrate.CapstoneProject.entities.Vehicle;

public class VehicleSpecification {

    public static Specification<Vehicle> hasBrandId(Long brandId) {
        return (root, query, cb) ->
                brandId == null ? null : cb.equal(root.get("brand").get("id"), brandId);
    }

    public static Specification<Vehicle> hasModelId(Long modelId) {
        return (root, query, cb) ->
                modelId == null ? null : cb.equal(root.get("model").get("id"), modelId);
    }
    public static Specification<Vehicle> isNotSold() {
        return (root, query, cb) -> cb.isFalse(root.get("sold"));
    }

    public static Specification<Vehicle> hasColor(String color) {
        return (root, query, cb) ->
                color == null || color.isBlank() ? null :
                        cb.like(cb.lower(root.get("color")), "%" + color.toLowerCase() + "%");
    }

    public static Specification<Vehicle> hasFuelType(String fuelType) {
        return (root, query, cb) ->
                fuelType == null || fuelType.isBlank() ? null :
                        cb.like(cb.lower(root.get("fuelType")), "%" + fuelType.toLowerCase() + "%");
    }

    public static Specification<Vehicle> priceGreaterThanOrEqual(Double minPrice) {
        return (root, query, cb) ->
                minPrice == null ? null : cb.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    public static Specification<Vehicle> priceLessThanOrEqual(Double maxPrice) {
        return (root, query, cb) ->
                maxPrice == null ? null : cb.lessThanOrEqualTo(root.get("price"), maxPrice);
    }

    public static Specification<Vehicle> yearGreaterThanOrEqual(Integer minYear) {
        return (root, query, cb) ->
                minYear == null ? null : cb.greaterThanOrEqualTo(root.get("yearOfConstruction"), minYear);
    }

    public static Specification<Vehicle> yearLessThanOrEqual(Integer maxYear) {
        return (root, query, cb) ->
                maxYear == null ? null : cb.lessThanOrEqualTo(root.get("yearOfConstruction"), maxYear);
    }

    public static Specification<Vehicle> kilometersLessThanOrEqual(Integer maxKm) {
        return (root, query, cb) ->
                maxKm == null ? null : cb.lessThanOrEqualTo(root.get("kilometers"), maxKm);
    }

    public static Specification<Vehicle> matchesSearch(String search) {
        return (root, query, cb) -> {
            if (search == null || search.isBlank()) return null;

            String like = "%" + search.toLowerCase() + "%";

            return cb.or(
                    cb.like(cb.lower(root.get("plateNumber")), like),
                    cb.like(cb.lower(root.get("brand").get("name")), like),
                    cb.like(cb.lower(root.get("model").get("name")), like),
                    cb.like(cb.lower(root.get("color")), like),
                    cb.like(cb.lower(root.get("fuelType")), like)
            );
        };
    }
}