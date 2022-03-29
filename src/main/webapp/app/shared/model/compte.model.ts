export interface ICompte {
  id?: number;
  numcom?: number;
  solde?: number;
}

export class Compte implements ICompte {
  constructor(public id?: number, public numcom?: number, public solde?: number) {}
}
