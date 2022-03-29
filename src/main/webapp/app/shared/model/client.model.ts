export interface IClient {
  id?: number;
  clientid?: number;
  nom?: string;
  prenom?: string;
  tel?: string;
  adresse?: string;
  email?: string;
  conseillerid?: number;
  userId?: number;
  compteId?: number;
}

export class Client implements IClient {
  constructor(
    public id?: number,
    public clientid?: number,
    public nom?: string,
    public prenom?: string,
    public tel?: string,
    public adresse?: string,
    public email?: string,
    public conseillerid?: number,
    public userId?: number,
    public compteId?: number
  ) {}
}
