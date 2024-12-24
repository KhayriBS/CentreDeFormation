// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  apiUrl: 'http://localhost:9095',
  apiSessionUrl: 'http://localhost:9095/session',
  apiParticipantUrl: 'http://localhost:9095/participant',
  baseUrlAuth : 'http://backend:9095/auth/',
  apiServerUrlDomaine: 'http://localhost:9095/domaine',
  apiServerUrlFormateur: 'http://localhost:9095/formateur',
  apiServerUrlFormation: 'http://localhost:9095/formation',
  apiServerUrlOrganisme: 'http://localhost:9095/organisme',
  apiServerUrlParticipant: 'http://localhost:9095/participant',
  apiServerUrlPays: 'http://localhost:9095/pays',
  apiServerUrlProfil: 'http://localhost:9095/profil',




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
