export interface IConseiller {
  id?: number;
  nom?: string;
  prenom?: string;
  tel?: string;
  email?: string;
  nomutilisateur?: string;
  motdepasse?: string;
  userId?: number;
}

export class Conseiller implements IConseiller {
  constructor(
    public id?: number,
    public nom?: string,
    public prenom?: string,
    public tel?: string,
    public email?: string,
    public nomutilisateur?: string,
    public motdepasse?: string,
    public userId?: number
  ) {}
}
