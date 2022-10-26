package model.data.format;

public class Line {
    static int id_counter = 0;
    private int id;

    private Boolean is_loop; //todo


    public Line(){
        this.id = id_counter;
        id_counter++;
    }

    public static int getId_counter() {
        return id_counter;
    }

    public int get_id() {
        return id;
    }

    public Boolean getIs_loop() {
        return is_loop;
    }

    public void setIs_loop(Boolean is_loop) {
        this.is_loop = is_loop;
    }
}
