package entity;

public class ViewTotal {
    private Integer id;
    private Double total;

    public ViewTotal() {
    }

    public ViewTotal(Integer id, Double total) {
        this.id = id;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ViewTotal{" +
                "id=" + id +
                ", total=" + total +
                '}';
    }
}
