import { Gateway } from "./gateway.model";

export class Peripheral {
    id?: any;
    uid?: number;
    vendor?: string;
    status?: string;
    updateAt?: any;
    gateway?: Gateway;
}
