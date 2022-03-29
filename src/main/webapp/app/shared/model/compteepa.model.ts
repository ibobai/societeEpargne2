export interface ICompteepa {
  id?: number;
  compteepaid?: number;
  tauxinteret?: number;
  plafond?: number;
  compteid?: number;
  compteId?: number;
}

export class Compteepa implements ICompteepa {
  constructor(
    public id?: number,
    public compteepaid?: number,
    public tauxinteret?: number,
    public plafond?: number,
    public compteid?: number,
    public compteId?: number
  ) {}
}
