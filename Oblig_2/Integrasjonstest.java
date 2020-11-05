class Integrasjonstest{ //Integrasjonstest som oppretter en av alle objektene og printer info om alt.
  public static void main(String [] args){
    Vanedannende vanedannende = new Vanedannende("Cosylan", 299.99, 250.0, 4);
    Spesialist spesialist = new Spesialist("Astrid Larsen", 15);
    Blaaresept blaaresept = new Blaaresept(vanedannende, spesialist, 5, 2);

    System.out.println(vanedannende);
    System.out.println(spesialist);
    System.out.println(blaaresept);
    System.out.println(blaaresept.prisAaBetale());
    vanedannende.settNyPris(149.99);
    System.out.println(blaaresept.prisAaBetale());

  }
}
