internal class Rectangle : Forme {
    public int Longueur { get; set; }
    public virtual int Largeur { get; set; }

    public override double Aire => Longueur * Largeur;

    public override double Perimetre => 2 * Largeur + 2 * Longueur;

    public string AireEtPerimetre => base.ToString();
    public override string ToString() {
        return $"Rectangle de longueur={Longueur} et largeur={Largeur}{Environment.NewLine}{base.ToString()}";
    }
}