// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  apiUrl: 'http://192.168.50.4:9095',
  apiSessionUrl: 'http://192.168.50.4:9095/session',
  apiParticipantUrl: 'http://192.168.50.4:9095/participant',
  baseUrlAuth: 'http://192.168.50.4:9095/auth/',
  apiServerUrlDomaine: 'http://192.168.50.4:9095/domaine',
  apiServerUrlFormateur: 'http://192.168.50.4:9095/formateur',
  apiServerUrlFormation: 'http://192.168.50.4:9095/formation',
  apiServerUrlOrganisme: 'http://192.168.50.4:9095/organisme',
  apiServerUrlPays: 'http://192.168.50.4:9095/pays',
  apiServerUrlProfil: 'http://192.168.50.4:9095/profil',




   // Utilisé pour ng serve
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
