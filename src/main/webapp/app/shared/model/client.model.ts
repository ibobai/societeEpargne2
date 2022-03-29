export interface IClient {
  id?: number;
  nom?: string;
  prenom?: string;
  tel?: string;
  adresse?: string;
  email?: string;
  userId?: number;
  compteId?: number;
}

export class Client implements IClient {
  constructor(
    public id?: number,
    public nom?: string,
    public prenom?: string,
    public tel?: string,
    public adresse?: string,
    public email?: string,
    public userId?: number,
    public compteId?: number
  ) {}
}
