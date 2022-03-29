export interface IComptecou {
  id?: number;
  comptecouid?: number;
  fraistrans?: number;
  soldemin?: number;
  compteid?: number;
  compteId?: number;
}

export class Comptecou implements IComptecou {
  constructor(
    public id?: number,
    public comptecouid?: number,
    public fraistrans?: number,
    public soldemin?: number,
    public compteid?: number,
    public compteId?: number
  ) {}
}
