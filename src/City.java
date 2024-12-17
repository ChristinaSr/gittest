public class City implements Comparable<City> {
    private int ID;
    private String name;
    private int population;
    private int CovidCases;

    City() {
        this.ID = 0;
        this.name = "";
        this.population = 0;
        this.CovidCases = 0;
    }

    City(int ID, String name, int population, int CovidCases) {
        this.ID = ID;
        this.name = name;
        this.population = population;
        this.CovidCases = CovidCases;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public int getCovidCases() {
        return CovidCases;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setCovidCases(int CovidCases) {
        this.CovidCases = CovidCases;
    }

    public double calculateDensity(int population, int CovidCases) {
        double x;
        x = (50000 * (double)CovidCases) / (double)population;
        x = Math.floor(x * 100) / 100;
        return x;
    }

    @Override
    public int compareTo(City city) {
        if (calculateDensity(this.population, this.CovidCases) == calculateDensity(city.population, city.CovidCases))
            return 0;
        else if (calculateDensity(this.population, this.CovidCases) > calculateDensity(city.population, city.CovidCases))
            return 1;
        else
            return -1;
    }
}
