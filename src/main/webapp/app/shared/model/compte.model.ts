export interface ICompte {
  id?: number;
  compteid?: number;
  numcom?: number;
  solde?: number;
  clientid?: number;
  compteId?: number;
}

export class Compte implements ICompte {
  constructor(
    public id?: number,
    public compteid?: number,
    public numcom?: number,
    public solde?: number,
    public clientid?: number,
    public compteId?: number
  ) {}
}
