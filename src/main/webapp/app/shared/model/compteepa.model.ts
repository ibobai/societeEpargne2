export interface ICompteepa {
  id?: number;
  tauxinteret?: number;
  plafond?: number;
  compteId?: number;
}

export class Compteepa implements ICompteepa {
  constructor(public id?: number, public tauxinteret?: number, public plafond?: number, public compteId?: number) {}
}
