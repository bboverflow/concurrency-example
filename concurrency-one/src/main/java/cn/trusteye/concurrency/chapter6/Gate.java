package cn.trusteye.concurrency.chapter6;

public class Gate {
    private int count = 0;
    private String name = "Nobody";
    private String address = "NoAddress";

    public  void cross(String name, String address) {
        count++;
        this.name = name;
        this.address = address;
        verify();
    }

    private void verify() {
        if (this.name.charAt(0) != this.address.charAt(0)) {
            System.out.println("----STOP-----" + toString());
        }
    }

    @Override
    public String toString() {
        return "Gate{" +
                "count=" + count +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
