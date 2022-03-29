import { Moment } from 'moment';

export interface ITransfert {
  id?: number;
  date?: Moment;
  montant?: number;
  typeoperation?: string;
  numcompte?: number;
  compteId?: number;
}

export class Transfert implements ITransfert {
  constructor(
    public id?: number,
    public date?: Moment,
    public montant?: number,
    public typeoperation?: string,
    public numcompte?: number,
    public compteId?: number
  ) {}
}
