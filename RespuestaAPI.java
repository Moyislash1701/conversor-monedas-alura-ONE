public record RespuestaAPI(String result, String base_code,ConversionRates conversion_rates) {

    public record ConversionRates(double USD, double EUR, double COP, double
    ARS,double BRL, double CLP){}
}

