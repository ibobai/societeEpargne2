export interface IComptecou {
  id?: number;
  fraistrans?: number;
  soldemin?: number;
  compteId?: number;
}

export class Comptecou implements IComptecou {
  constructor(public id?: number, public fraistrans?: number, public soldemin?: number, public compteId?: number) {}
}
