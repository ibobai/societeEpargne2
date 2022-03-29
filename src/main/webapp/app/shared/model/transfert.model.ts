import { Moment } from 'moment';

export interface ITransfert {
  id?: number;
  transfertid?: number;
  date?: Moment;
  montant?: number;
  typeoperation?: string;
  numcompte?: number;
  conseillerid?: number;
  compteId?: number;
}

export class Transfert implements ITransfert {
  constructor(
    public id?: number,
    public transfertid?: number,
    public date?: Moment,
    public montant?: number,
    public typeoperation?: string,
    public numcompte?: number,
    public conseillerid?: number,
    public compteId?: number
  ) {}
}
