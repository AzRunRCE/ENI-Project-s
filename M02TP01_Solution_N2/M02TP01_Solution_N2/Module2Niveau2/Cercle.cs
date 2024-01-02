public class Cercle : Forme {
    public int Rayon { get; set; }

    public override double Aire { get { return Rayon * Rayon * 3.14; } }

    public override double Perimetre => 2 * Math.PI * Rayon;


    public override string ToString() {
        return $"Cercle de rayon {Rayon}{Environment.NewLine}{base.ToString()}";
    }
}